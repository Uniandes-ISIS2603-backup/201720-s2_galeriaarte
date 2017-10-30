(function (ng) {
    // Definición del módulo
    var mod = ng.module("clienteModule", ['ui.router']);
    // Configuración de los estados del módulo
    mod.config(['$stateProvider', '$urlRouterProvider', 
        function ($stateProvider, $urlRouterProvider) {
            // En basePath se encuentran los templates y controladores de módulo
            var basePath = 'src/modules/clientes/';
            // Mostrar la lista de editoriales será el estado por defecto del módulo
            $urlRouterProvider.otherwise("/clientesList");
            // Definición del estado 'editorialsList' donde se listan los editoriales
            $stateProvider.state('clientesList', {
                // Url que aparecerá en el browser
                url: '/clientes/list',
                views: {
                    'mainView': {
                        templateUrl: basePath + 'clientes.list.html',
                        controller: 'clienteCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('clienteCreate', {
                url: '/clientes/create',
                views: {
                    'mainView': {
                        controller: 'clientesNewCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + '/clientes.create.html'
                    }
                }

            });
        }
    ]);
})(window.angular);
