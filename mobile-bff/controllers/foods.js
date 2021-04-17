module.exports = {
    getFoods: (req, res) => {
        res.json(mockFoods())
    }
}

const mockFoods = () => {
    var foods = []
    for (var index = 0; index < 20; index++) {
        var food = { id: index.toString(), name: `鸡排饭${index}`, priceCent: 3200, imageUrl: "https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=2481205946,34418544&fm=26&gp=0.jpg" }
        foods.push(food)
    }
    return foods
}