print("Server started")
from MyTcpServer import MyTcpServer
import socket
import cv2
import numpy

s = MyTcpServer()
s.wait_and_connect("localhost", 5000)
cv2.namedWindow("Server")

while True:
    # receive bytes to read and bytes
    len = int.from_bytes(s.recvall(2048), byteorder='big')
    if not len:  # check if packet received is not null
        break   # if so, it means client closed the connection or an error occured

    data = s.recvall(len)
    if not data:    # check if packet received is not null
        break   # if so, it means client closed the connection or an error occured

    # convert bytes to ndarray
    mat = numpy.ndarray(shape=(len, 1), dtype=numpy.uint8, buffer=data)
    frame = cv2.imdecode(mat, 1)
    cv2.imshow("Server", frame)

    key = cv2.waitKey(10)
    if key != -1:
        break

s.close()
cv2.destroyAllWindows()
