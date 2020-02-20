import socket


class MyTcpServer:
    def __init__(self, sock=None):
        self.conn = None
        self.addr = None
        if sock is None:
            self.sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        else:
            self.sock = sock

    def wait_and_connect(self, host, port):
        self.sock.bind((host, port))
		print("Waiting for a connection")
        self.sock.listen()
        self.conn, self.addr = self.sock.accept()
        print("Connected by", self.addr)

    def send(self, msg):
        self.sock.send(msg.to_bytes(len(msg), 'big'))

    def recvall(self, count):
        return self.conn.recv(count)

    def close(self):
        self.conn.close()
        print("Connection closed")
