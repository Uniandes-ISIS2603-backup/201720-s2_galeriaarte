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
        ,'catalogoModule'
        ,'obraModule',
        'hojaVidaModule',
        'loginModule'
        

    ]);
    // Resuelve problemas de las promesas
    app.config(['$qProvider', function ($qProvider) {
            $qProvider.errorOnUnhandledRejections(false);
        }]);
    
    app.run(['$rootScope', '$transitions', function ($rootScope, $transitions) {
            
            $transitions.onSuccess({to: '*'}, function (trans) {

                var $state = trans.router.stateService;
                var requireLogin = $state.current.data.requireLogin;
                var roles = $state.current.data.roles;
               

                $rootScope.isAuthenticated = function () {

                    if (sessionStorage.getItem("username") !== null) {
                        $rootScope.currentUser = sessionStorage.getItem("name");
                        $rootScope.currentId = sessionStorage.getItem("idUsuario");
                        return true;
                    } else {
                        return false;
                    }
                };
                
                $rootScope.hasPermissions = function () {
                    if (($rootScope.isAuthenticated) && (roles.indexOf(sessionStorage.getItem("rol")) > -1) && (roles.indexOf(sessionStorage.getItem("rol")) < 1)) {
                        return true;
                    } else {
                        return false;
                    }
                };

                if (requireLogin && (sessionStorage.getItem("username") === null)) {
                    event.preventDefault();
                    $state.go('login', $state.params);
                }

            });
    }])
})(window.angular);
