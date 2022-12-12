


mongoose.connect('mongodb://localhost/dis');
var db = mongoose.connection;
db.on('error', console.log("Mongodb connection error"));
db.once('open', function (callback)
{
    console.log("MongoDB opened");
});