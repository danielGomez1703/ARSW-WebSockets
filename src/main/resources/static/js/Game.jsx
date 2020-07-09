/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * 
 */

const { Component } = React;
const { render } = ReactDOM;

// Retorna la url del servicio. Es una función de configuración.
function BBServiceURL() {
    var host = window.location.host;
    var sala = sessionStorage.getItem("sala");
    var url = 'wss://' + (host) + '/bbService/'+sala;
    console.log("URL Calculada: " + url);
    return url;
}
class WSBBChannel {
    constructor(URL, callback) {
        this.URL = URL;
        this.wsocket = new WebSocket(URL);
        this.wsocket.onopen = (evt) => this.onOpen(evt);
        this.wsocket.onmessage = (evt) => this.onMessage(evt);
        this.wsocket.onerror = (evt) => this.onError(evt);
        this.receivef = callback;
    }
    onOpen(evt) {
        console.log("In onOpen", evt);
    }
    onMessage(evt) {
        console.log("In onMessage", evt);
        // Este if permite que el primer mensaje del servidor no se tenga en cuenta.
        // El primer mensaje solo confirma que se estableció la conexión.
        // De ahí en adelante intercambiaremos solo puntos(x,y) con el servidor
        if (evt.data !== "Connection established.") {
            this.receivef(evt.data);
        }
    }
    onError(evt) {
        console.error("In onError", evt);
    }
    send(envio) {
        let msg = envio;
        //JSON.stringify({mensaje: envio});
        // console.log("sending: ", msg);
        this.wsocket.send(msg);
    }
}

function Square(props) {
    return (
            <button  type="button" className="square" onClick={props.onClick}>
                {props.value}
            </button>
            );
}

class Board extends React.Component {
    renderSquare(i) {
        return (
                <Square
                    value={this.props.squares[i]}
                    onClick={() => this.props.onClick(i)}
                    />
                );
    }
    render() {
        return (
                <div>
                    <div className="board-row">
                        {this.renderSquare(0)}
                        {this.renderSquare(1)}
                        {this.renderSquare(2)}
                    </div>
                    <div className="board-row">
                        {this.renderSquare(3)}
                        {this.renderSquare(4)}
                        {this.renderSquare(5)}
                    </div>
                    <div className="board-row">
                        {this.renderSquare(6)}
                        {this.renderSquare(7)}
                        {this.renderSquare(8)}
                    </div>
                </div>
                );
    }
}

class Game extends React.Component {
    constructor(props) {
        super(props);

        this.comunicationWS =
                new WSBBChannel(BBServiceURL(),
                        (msg) => {
                    var obj = JSON.parse(msg);
                    //          console.log("On func call back ", msg);
                    this.sendMessage(obj);
                });
        this.state = {
            history: [
                {
                    squares: Array(9).fill(null)
                }],
            stepNumber: 0,
            xIsNext: true,
            mensaje: "",
            mensajesR: [],
            mensajesS: []

        };

        this.publish = this.publish.bind(this);
        this.handleChange = this.handleChange.bind(this);

    }
    componentDidMount() {
        this.timerID = setInterval(
                () => (this.render(),
                    1000)
        );
    }

    sendMessage(estado) {


//        this.state.mensajesR.push(msg);
//        this.state.mensajesS.push(null);
        //  console.log("--------Nuevo Estado----"+ estado);
        this.setState(estado);
        this.setState(
                [this.state.mensajesR, this.state.mensajesS] = [this.state.mensajesS, this.state.mensajesR]
                );

        //console.log(this.state);

    }

    publish() {

        this.state.mensajesS.push(this.state.mensaje);
        this.state.mensajesR.push(null);
        this.comunicationWS.send(JSON.stringify(this.state));
        this.setState({mensaje: ""});
    }

    handleChange( { target }) {
        this.setState({
            [target.name]: target.value
        });
    }

    handleClick(i) {

        const history = this.state.history.slice(0, this.state.stepNumber + 1);
        const current = history[history.length - 1];
        const squares = current.squares.slice();
        if (calculateWinner(squares) || squares[i]) {
            return;
        }
        squares[i] = this.state.xIsNext ? "X" : "O";
        this.setState({
            history: history.concat([
                {
                    squares: squares
                }
            ]),
            stepNumber: history.length,
            xIsNext: !this.state.xIsNext
        }, () => {
            //let wsreference = this.comunicationWS;
            this.comunicationWS.send(JSON.stringify(this.state));

        });
    }

    jumpTo(step) {
        this.setState({
            stepNumber: step,
            xIsNext: (step % 2) === 0
        });
    }

    render() {
        const history = this.state.history;
        const current = history[this.state.stepNumber];
        const winner = calculateWinner(current.squares);
        const moves = history.map((step, move) => {
            const desc = move ?
                    'Go to move #' + move :
                    'Go to game start';
            return (
                    <li key={move}>
                    
                        <button onClick={() => this.jumpTo(move)}>{desc}</button>
                    </li>
                    );
        });
        let status;
        if (winner) {
            status = "Winner: " + winner;
        } else {
            status = "Next player: " + (this.state.xIsNext ? "X" : "O");
        }
        let listMensajeR = this.state.mensajesR;
        let listMensajeS = this.state.mensajesS;
        return (
                <div>
                    <div className="container">
                        <div className="row justify-content-md-center">
                
                            <div className="game">
                                <div className="game-board">
                                    <Board
                                        squares={current.squares}
                                        onClick={i => this.handleClick(i)}
                                        />
                                </div>
                                <div className="game-info">
                                    <div>{status}</div>
                                    <ol>{moves}</ol>
                                </div>
                            </div>
                        </div>
                        <div className="row">
                            <div className="input-group mb-3">
                                <input type="text" className="form-control" name ="mensaje" placeholder="Enviar Mensaje ... " onChange={this.handleChange} />
                                <div className="input-group-append">
                                    <button className="btn btn-outline-secondary" type="button" onClick= {this.publish}> Send </button>
                                </div>
                                <hr/>
                            </div>
                        </div>              
                        <div className="row">
                            <div className="col-md-6">
                                <ul className="list-group">RECIBIDOS:
                                    { listMensajeR.map((cadena, index) => (
                                        <li key={index} className="list-group-item active"> {cadena} </li>
                                                    ))}
                                </ul>
                            </div>
                
                            <div className="col-md-6">
                
                                <ul>ENVIADOS:
                                    { listMensajeS.map((cadena, index) => (
                                        <li key={index} className="list-group-item"> {cadena} </li>
                                                    ))}
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>

                );
    }

}


ReactDOM.render(<Game />, document.getElementById("root"));

function calculateWinner(squares) {
    const lines = [
        [0, 1, 2],
        [3, 4, 5],
        [6, 7, 8],
        [0, 3, 6],
        [1, 4, 7],
        [2, 5, 8],
        [0, 4, 8],
        [2, 4, 6]
    ];
    for (let i = 0; i < lines.length; i++) {
        const [a, b, c] = lines[i];
        if (squares[a] && squares[a] === squares[b] && squares[a] === squares[c]) {
            return squares[a];
        }
    }
    return null;
}
