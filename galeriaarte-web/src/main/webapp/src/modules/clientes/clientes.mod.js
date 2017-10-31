(function (ng) {
    // Definición del módulo
    var mod = ng.module("clienteModule", ['ui.router']);
    mod.constant("clientesContext", "api/cliente");
    // Configuración de los estados del módulo
    mod.config(['$stateProvider', '$urlRouterProvider', 
        function ($stateProvider, $urlRouterProvider) {
            // En basePath se encuentran los templates y controladores de módulo
            var basePath = 'src/modules/clientes/';
            // Mostrar la lista de editoriales será el estado por defecto del módulo
            $urlRouterProvider.otherwise("/clientesList");
            // Definición del estado 'editorialsList' donde se listan los editoriales
            $stateProvider.state('clientes', {
                url: '/clientes',
                abstract: true,
                views: {
                    'mainView': {
                        templateUrl: basePath + 'clientes.html',
                        controller: 'clienteCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('clientesList', {
                // Url que aparecerá en el browser
                url: '/list',
                parent: 'clientes',
                views: {
//                    'mainView': {
                    'listView': {
                        templateUrl: basePath + 'clientes.list.html',
//                        controller: 'clienteCtrl',
//                        controllerAs: 'ctrl'
                    }
                }
            }).state('clienteCreate', {
                url: '/create',
                parent: 'clientes',
                views: {
                    'mainView': {
                        templateUrl: '/new/clientes.new.html',
                        controller: 'clienteNewCtrl'
                    }
                }

            });
        }
    ]);
})(window.angular);
