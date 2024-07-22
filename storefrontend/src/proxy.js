const { createProxyMiddleware } = require('http-proxy-middleware');

module.exports = function(app) {
  app.use(
    '/api', 
    createProxyMiddleware({
      target: 'http://localhost:8080', 
      changeOrigin: true,
      pathRewrite: {
      },
      onProxyReq: (proxyReq)=> {
        if (userContext != null) {
            proxyReq.setHeader("JWT", userContext.jwt)
        }
      },
      onProxyRes: (proxyRes)=> {
        if(proxyRes.data.error.includes('Token expired')){
            
        }
      }
    })

  );
  app.listen(3000);
};