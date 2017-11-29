(function (ng) {
    var mod = ng.module("pagoModule", ['ui.router']);
    mod.constant("pagosContext", "api/pago");
    mod.config(['$stateProvider', '$urlRouterProvider', 
        function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/pagos/';
            $urlRouterProvider.otherwise("/pagosList");
            
            $stateProvider.state('pagos', {
                url: '/pagos',
                abstract: true,
                views: {
                    'mainView': {
                        templateUrl: basePath + 'pagos.html',
                        controller: 'pagoCtrl',
                        controllerAs: 'ctrl'
                    }
                }
                ,
                data:{
                    requireLogin: false,
                    roles: ['administrador', 'cliente', 'artista']
                }
            }).state('pagosList', {
                url: '/list',
                parent: 'pagos',
                views: {
                    'listView': {
                        templateUrl: basePath + 'pagos.list.html'
                    }
                }
            }).state('pagoDetail', {
                url: '/{pagoId:int}/detail',
                parent: 'pagos',
                param: {
                    pagoId: null
                },
                views: {
                    'listView': {
                        templateUrl: basePath + 'pagos.list.html'
                    },
                    'detailView': {
                        templateUrl: basePath + 'pagos.detail.html',
                        controller: 'pagoCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('pagoCreate', {
                url: '/create',
                parent: 'pagos',
                views: {
                    'detailView': {
                        templateUrl: basePath + 'new/pagos.new.html',
                        controller: 'pagoNewCtrl'
                    }
                }
                ,
                data: {
                    requireLogin: false,
                    roles: ['administrador', 'cliente','artista']
                }
            
            }).state('pagoDelete', {
                url: '/delete/{pagoId:int}',
                parent: 'pagos',
                param: {
                    pagoId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + 'delete/pagos.delete.html',
                        controller: 'pagoDeleteCtrl',
                        controllerAs: 'ctrl'
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


