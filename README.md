# ChallengeClariana

#Nivel 1
  - Api Rest creada en Java Spring Boot
  - BD Mysql

#Nivel 2
  - Api hosteada en Google Cloud
  - Instructivo para consumir la api de validación ADN:
  - URL: https://challengeclariana.uc.r.appspot.com/api/mutant/isMutant
  - Tipo: POST
  - Request (ejemplo):
      { "dna":["ATGCGA","CAGTGC","TTATTT","AGACGG","GCGTCA","TCACTG"] }

  - Condiciones request:
    - La estructura debe contemplar un arreglo N*N, sino generará BadRequest 403
    - Solamente se aceptan las letras (A,C,G,T), sino generará BadRequest 403
    
  - Response:
    - Si es mutante la api responderá con estatus Ok 200.
    - Si no es mutante la api responderá con status 403.
    - El incumplimiento de las condiciones generará como respuesta un status 403.
    
#Nivel 3
 - Cada ADN verificado se estarán registrando en la BD:
    - Locación: mysql://34.123.159.205:3306/springdb (tabla: historico_adn)
    - Usuario: Evaluador
    - Evaluador: Evaluador
    
 - Instructivo para consumir la api para consultar estadísticas:
    - URL: https://challengeclariana.uc.r.appspot.com/api/mutant/stats 
    - Tipo: GET 
    - Response (ejemplo): 
      { "count_mutant_dna": 4, "count_human_dna": 10, "ratio": 0.4 }
