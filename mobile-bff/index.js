const express = require('express')
const foods = require("./controllers/foods")
const app = express()
const port = 3000

app.get('/customer/foods', foods.getFoods)

app.listen(port, () => {
  console.log(`Mobile BFF listening at http://localhost:${port}`)
})