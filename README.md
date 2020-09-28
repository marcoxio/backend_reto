## PayLoad Petición Post
### Datos
* Url: `http://localhost:9081/api/v1/reporte`

# Ejemplo de Data
{
	"idTecnico" : "1",
	"idServicio": "1",
	"fechaInicio": "2020-01-01T09:00:00",
	"fechaFin": "2020-01-01T11:00:00"
}

{
	"idTecnico" : "1",
	"idServicio": "2",
	"fechaInicio": "2020-01-01T11:00:00",
	"fechaFin": "2020-01-01T15:00:00"
}

{
	"idTecnico" : "1",
	"idServicio": "3",
	"fechaInicio": "2020-01-01T15:00:00",
	"fechaFin": "2020-01-01T20:00:00"
}

{
	"idTecnico" : "1",
	"idServicio": "4",
	"fechaInicio": "2020-01-02T09:00:00",
	"fechaFin": "2020-01-02T12:00:00"
}

{
	"idTecnico" : "1",
	"idServicio": "4",
	"fechaInicio": "2020-01-02T13:00:00",
	"fechaFin": "2020-01-02T20:00:00"
}

# Petición Get
`http://localhost:9081/api/v1/reporte`

# Petición Consulta Horas Get
`http://localhost:9081/api/v1/calculo/idTecnico/1/semana/1`