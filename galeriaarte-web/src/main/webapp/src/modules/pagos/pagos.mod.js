(function (ng) {
    var mod = ng.module("pagoModule", ['ui.router']);
    mod.constant("pagoContext", "api/pagos");
    mod.config(['$stateProvider', '$urlRouterProvider', 
        function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/pagos/';
            var basePathCompras = 'src/modules/compras/';
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
//                        templateUrl: basePathCompras + 'compras.list.html',
//                        controller: 'pagoCtrl',
//                        controllerAs: 'ctrl'
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
            });
        }]);
})(window.angular);


