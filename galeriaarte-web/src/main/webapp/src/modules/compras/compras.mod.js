(function (ng) {
    var mod = ng.module("compraModule", ['ui.router']);
    mod.constant("comprasContext", "api/compras");
    mod.config(['$stateProvider', '$urlRouterProvider', 
        function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/compras/';
            var basePathPagos = 'src/modules/pagos/';         
            $urlRouterProvider.otherwise("/comprasList");
            
            $stateProvider.state('compras', {
                url: '/compras',
                abstract: true,
                views: {
                    'mainView': {
                        templateUrl: basePath + 'compras.html',
                        controller: 'compraCtrl',
                        controllerAs: 'ctrl'
                    }
                }
                ,
                data: {
                    requireLogin: false,
                    roles: ['administrador', 'cliente','artista']
                }
            }).state('comprasList', {
                url: '/list',
                parent: 'compras',
                views: {
                    'listView': {
                        templateUrl: basePath + 'compras.list.html'
                    }
                }
            }).state('compraDetail', {
                url: '/{compraId:int}/detail',
                parent: 'compra',
                param: {
                    compraId: null
                },
                views: {
                    'listView': {
                        templateUrl: basePathPagos + 'pagos.list.html',
                        controller: 'pagoCtrl',
                        controllerAs: 'pagoCtrl'
                    },
                    'detailView': {
                        templateUrl : basePath + 'compras.detail.html',
                        controller: 'compraCtrl',
                        controllerAs: 'compraCtrl'
                    }
                }
            }).state('compraCreate', {
                url: '/create',
                parent: 'compras',
                views: {
                    'detailView': {
                        templateUrl: basePath + 'new/compras.new.html',
                        controller: 'compraNewCtrl'
                    }
                }
                ,
                data: {
                    requireLogin: false,
                    roles: ['administrador', 'cliente','artista']
                }
            }).state('compraUpdate', {
                url: '/update/{compraId:int}',
                parent: 'compras',
                param: {
                    comprad: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + 'new/compras.new.html',
                        controller: 'compraUpdateCtrl'
                    }
                }
                ,
                data: {
                    requireLogin: false,
                    roles: ['administrador', 'cliente','artista']
                }
            }).state('compraDelete', {
                url: '/delete/{compraId:int}',
                parent: 'compras',
                param: {
                    compraId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + 'delete/compra.delete.html',
                        controller: 'compraDeleteCtrl',
                        controllerAs: 'compraDeleteCtrl'
                    }
                }
                ,
                data: {
                    requireLogin: false,
                    roles: ['administrador', 'cliente','artista']
                }
            });
        }]);
})(window.angular);




