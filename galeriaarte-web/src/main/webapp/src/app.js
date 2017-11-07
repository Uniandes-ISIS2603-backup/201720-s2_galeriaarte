
(function (ng) {
    var app = angular.module('mainApp', [
        // External dependencies
        'ui.router',
        'ui.bootstrap',
        // Internal modules dependencies
        'clienteModule',
        'comentarioModule',
        'marcoModule',
        'blogModule',
        'artistaModule',
        'compraModule',
        'pagoModule'
        ,'catalogoModule',
        ,'obraModule',
        'hojaVidaModule'
        

    ]);
    // Resuelve problemas de las promesas
    app.config(['$qProvider', function ($qProvider) {
            $qProvider.errorOnUnhandledRejections(false);
        }]);
})(window.angular);