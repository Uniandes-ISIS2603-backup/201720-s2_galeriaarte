(function (ng) {
    var mod = ng.module("catalogoModule", ['ui.router']);
    mod.constant("catalogosContext", "api/catalogos");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/catalogos/';
            $urlRouterProvider.otherwise("/catalogosList");

            $stateProvider.state('catalogos', {
                url: '/catalogos',
                abstract: true,
                 views: {
                    'mainView': {
                        templateUrl: basePath + 'catalogos.html',
                        controller: 'catalogoCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('catalogosList', {
                url: '/list',
                parent: 'catalogos',
                views: {
                    'listView': {
                        templateUrl: basePath + 'catalogos.list.html'
                    }
                }
            }).state('catalogoDetail', {
                url: '/{catalogoId:int}/detail',
                parent: 'catalogos',
                param: {
                    catalogoId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + 'catalogos.detail.html',
                        controller: 'catalogoCtrl',
                        controllerAs: 'ctrl'
                    }

                }

            }).state('catalogosCreate', {
                url: '/create',
                parent: 'catalogos',
                views: {
                    'detailView': {
                        templateUrl: basePath + '/new/catalogos.new.html',
                        controller: 'catalogoNewCtrl'
                    }
                }
            }).state('catalogoUpdate', {
                url: '/update/{catalogoId:int}',
                parent: 'catalogos',
                param: {
                    catalogoId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + '/new/catalogos.new.html',
                        controller: 'catalogoUpdateCtrl'
                    }
                }
            }).state('catalogoDelete', {
                url: '/delete/{catalogoId:int}',
                parent: 'catalogos',
                param: {
                   catalogoId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + '/delete/catalogo.delete.html',
                        controller: 'catalogoDeleteCtrl'
                    }
                }
            });
        }]);
})(window.angular);