# https://projects.raspberrypi.org/en/projects/see-like-a-bat/10

from gpiozero import InputDevice, OutputDevice, PWMOutputDevice
from time import sleep, time
from gps import *
from compass import *

# sleep(2)
motor1, motor2, motor3, motor4, motor5, motor6, motor7, motor8 = 0


class Motorini:

    def __init__(self):
        # PWMOutputDevice richiede un numero con la virgola tra 0 e 1
        self.motor1 = PWMOutputDevice(14)  # addome destro
        self.motor2 = PWMOutputDevice(15)  # addome destro
        self.motor3 = PWMOutputDevice(18)  # addome centro
        self.motor4 = PWMOutputDevice(27)  # addome centro
        self.motor5 = PWMOutputDevice(22)  # addome sinistro
        self.motor6 = PWMOutputDevice(23)  # addome sinistro
        self.motor7 = PWMOutputDevice(24)  # gamba destra -> vibra se deve fermarsi
        self.motor8 = PWMOutputDevice(25)  # gamba sinistra -> vibra se deve fermarsi

    def calcola_vibrazione(self, angolo):
        return 0

    # vibrazione = (((distance - 0.02) * -1) / (4 - 0.02)) + 1
    # return vibrazione

    def controlla_se_deve_fermarsi(self, immagine):
        return 0

    # qui ci sarà un boolean che dirà se bisogna fermarsi (es. a causa del semaforo rosso)
    # return esito

    def ricevi_richiesta(self):
        return 0

    def preleva_da_direzione(self, richiesta):
        return 0

    def loop(self):
        while True:
            richiesta = self.ricevi_richiesta()
            angolo = self.preleva_da_direzione(richiesta)
            vibrazione = 0.77  # calcola_vibrazione(angolo)
            # esito = controlla_se_deve_fermarsi(immagine)
            if 0 <= angolo <= 60:
                motor1.value = vibrazione
                motor2.value = vibrazione
            elif 60 < angolo <= 120:
                motor3.value = vibrazione
                motor4.value = vibrazione
            elif 120 < angolo <= 180:
                motor5.value = vibrazione
                motor6.value = vibrazione
