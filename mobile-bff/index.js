const express = require('express')
const app = express()
const port = 3000

app.get('/customer/foods', (req, res) => {
  var foods = []
  for (var index = 0; index < 20; index++) {
    var food={name:`鸡排饭${index}`,priceCent:3200, imageUrl:"https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=2481205946,34418544&fm=26&gp=0.jpg"}
    foods.push(food)
  }
  res.json(foods)
})

app.listen(port, () => {
  console.log(`Mobile BFF listening at http://localhost:${port}`)
})