import socket

class MyTcpClient:

    def __init__(self, sock=None):
        if sock is None:
            self.sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        else:
            self.sock = sock

    def connect(self, host=None, port=None):
        if host is None and port is None:
            self.sock.connect(("127.0.0.1", "5000"))
        else:
            self.sock.connect((host, port))

    def send(self, msg):
        totalsent = 0
        msglen = len(msg)

        while totalsent < msglen:
            sent = self.sock.send(msg[totalsent:])
            if sent == 0:
                raise RuntimeError("Socket connection broken")
            totalsent += sent

    def receive(self):
        chunks = []
        bytes_recd = 0
        msglen = self.sock.recv(2048)
        while bytes_recd < msglen:
            chunk = self.sock.recv(min(msglen - bytes_recd, 8192))
            if chunk == b'':
                raise RuntimeError("socket connection broken")
            chunks.append(chunk)
            bytes_recd = bytes_recd + len(chunk)
        return b''.join(chunks)

    def close(self):
        self.sock.close()
