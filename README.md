# WeatherSensor REST API

This is REST API for weather sensors, which send measurements: <b>double</b> temperature, <b>boolean</b> raining, <b>Sensor</b> sensor (just name).

## Request descriptions

### Get all measurements:

`GET /measurements`

### Body of request example

  {
  
      "value":44.4,
      "raining":true,
      "sensor":{
          "name": "sensor_2"
      }
      
  }


### Response example

 [

    {
        "value": -61.39,
        "raining": false,
        "sensor": {
            "name": "sensor_1"
        }
    },
    {
        "value": 6.64,
        "raining": false,
        "sensor": {
            "name": "sensor_1"
        }
    }
    
 ]


### Add new mesurement:

`POST /measurement/add`

### Body of request example


{
  
      "name":"sensor_2",
      
}

### Response without exceptions:

"OK"

### Get all sensors:

`GET /sensors`

### Response example

[

    {
        "name": "sensor_1"
    },
    {
        "name": "sensor_2"
    }

]

### Add new sensors:

`POST /sensors/registration`

### Response example without exceptions

"OK"
