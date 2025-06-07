import json
import requests
import datetime
 
class Mindicador:
 
    def __init__(self, indicador, date):
        self.indicador = indicador
        #self.year = year
        self.date = datetime.datetime.now()
 
    def InfoApi(self):
    # En este caso hacemos la solicitud para el caso de consulta de un indicador en un año determinado
        url = f'https://mindicador.cl/api/{self.indicador}' #/{self.year}'
        response = requests.get(url)
        data = json.loads(response.text.encode("utf-8"))
    # Para que el json se vea ordenado, retornar pretty_json
        pretty_json = json.dumps(data, indent=2)
        print(pretty_json)
        return data

print("Bienvenido a la API de Mindicador")
indicador = 'uf' #input("Ingrese el indicador que desea consultar (ejemplo: ivp, uf, dolar, euro): ")
#year = input("Ingrese el año que desea consultar (ejemplo: 2023): ")
date = datetime.datetime.now()
indicador_obj = Mindicador(indicador, date)
info = indicador_obj.InfoApi()
print(f"Información del indicador {indicador} para hoy:")
# Imprimir el valor del indicador para la fecha de hoy

print(f'Valor UF fecha de hoy {date}: ${info["serie"][0]["valor"]} {info["unidad_medida"]} FECHA: {info["serie"][0]["fecha"]}')