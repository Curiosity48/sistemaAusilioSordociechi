# https://stackoverflow.com/questions/20820602/image-send-via-tcp
import socket


class MyTcpClient:
    def __init__(self, sock=None):
        if sock is None:
            self.sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        else:
            self.sock = sock

    def connect(self, host, port):
        self.sock.connect((host, port))
        print("Connected to server")

    def send(self, msg):
        self.sock.send(msg)

    def recvall(self, count):
        return self.sock.recv(count)

    def close(self):
        self.sock.close()
        print("Connection closed")
