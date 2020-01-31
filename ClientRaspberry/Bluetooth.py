import bluetooth


class Bluetooth:

    def __init__(self):
        nearby_devices = bluetooth.discover_devices()
        print("Seleziona dispositivo bluetooth:")
        i = 0
        for name, addr in nearby_devices:
            print(" %s - %s" % (addr, name))
        disp = input()
        mac_address = nearby_devices[disp].addr

    def receiveMessages(self):
        server_sock = bluetooth.BluetoothSocket(bluetooth.RFCOMM)

        port = 1
        server_sock.bind(("", port))
        server_sock.listen(1)

        client_sock, address = server_sock.accept()
        print("Accepted connection from " + str(address))

        data = client_sock.recv(1024)
        print("received [%s]" % data)

        client_sock.close()
        server_sock.close()

    def sendMessageTo(self):
        port = 1
        sock = bluetooth.BluetoothSocket(bluetooth.RFCOMM)
        sock.connect((self.mac_address, port))
        sock.send("hello!!")
        sock.close()