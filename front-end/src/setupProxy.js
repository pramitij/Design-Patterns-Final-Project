var proxy = require('http-proxy-middleware');
 const BASE_ROUTE = 'http://localhost:8080/';

module.exports = app => {
    /*To enable CORS BEGIN*/
    app.use(function (req, res, next) {
      res.header("Access-Control-Allow-Origin", "*");
      res.header(
        "Access-Control-Allow-Headers",
        "Access-Control-Allow-Headers, Access-Control-Allow-Origin, Origin, X-Requested-With, Content-Type, Accept,Authorization"
      );
      next();
    });

    app.use('/api', proxy({ target: BASE_ROUTE, 
      pathRewrite: {
      '^/api/': '/' // remove base path
    }, changeOrigin: true }));

  };