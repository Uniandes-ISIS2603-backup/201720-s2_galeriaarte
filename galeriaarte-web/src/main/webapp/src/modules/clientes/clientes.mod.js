(function (ng) {
    var mod = ng.module("clienteModule", ['ui.router']);
    mod.constant("clientesContext", "api/cliente");
    mod.config(['$stateProvider', '$urlRouterProvider', 
        function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/clientes/';
            var basePathComentarios = 'src/modules/comentarios/';         
            $urlRouterProvider.otherwise("/clientesList");
            
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
                ,
                data: {
                    requireLogin: false,
                    roles: ['admin', 'cliente']
                }
            }).state('clientesList', {
                url: '/list',
                parent: 'clientes',
                views: {
                    'listView': {
                        templateUrl: basePath + 'clientes.list.html'
                    }
                }
            }).state('clienteDetail', {
                url: '/{clienteId:int}/detail',
                parent: 'clientes',
                param: {
                    clienteId: null
                },
                views: {
                    'listView': {
                        templateUrl: basePathComentarios + 'comentarios.list.html',
                        controller: 'clienteCtrl',
                        controllerAs: 'ctrl'
                    },
                    'detailView': {
                        templateUrl : basePath + 'clientes.detail.html',
                        controller: 'clienteCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('clienteCreate', {
                url: '/create',
                parent: 'clientes',
                views: {
                    'detailView': {
                        templateUrl: basePath + 'new/clientes.new.html',
                        controller: 'clienteNewCtrl'
                    }
                },
                data: {
                    requireLogin: true,
                    roles: ['admin']
                }
            }).state('clienteUpdate', {
                url: '/update/{clienteId:int}',
                parent: 'clientes',
                param: {
                    clienteId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + 'new/clientes.new.html',
                        controller: 'clienteUpdateCtrl'
                    }
                },
                data: {
                    requireLogin: true,
                    roles: ['admin', 'cliente']
                }
            }).state('clienteDelete', {
                url: '/delete/{clienteId:int}',
                parent: 'clientes',
                param: {
                    clienteId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + 'delete/clientes.delete.html',
                        controller: 'clienteDeleteCtrl',
                        controllerAs: 'ctrl'
                    }
                },
                data: {
                    requireLogin: true,
                    roles: ['admin']
                }
            });
        }]);
})(window.angular);
