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
                ,
                data: {
                    requireLogin: false,
                    roles: ['admin', 'assistant']
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
                url: '/{idCatalogo:int}/detail',
                parent: 'catalogos',
                param: {
                    idCatalogo: null
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
                },
                data: {
                    requireLogin: true,
                    roles: ['admin']
                }
            }).state('catalogoUpdate', {
                url: '/update/{idCatalogo:int}',
                parent: 'catalogos',
                param: {
                    idCatalogo: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + '/new/catalogos.new.html',
                        controller: 'catalogoUpdateCtrl'
                    }
                },
                data: {
                    requireLogin: true,
                    roles: ['admin', 'assistant']
                }
            }).state('catalogoDelete', {
                url: '/delete/{idCatalogo:int}',
                parent: 'catalogos',
                param: {
                   idCatalogo: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + '/delete/catalogo.delete.html',
                        controller: 'catalogoDeleteCtrl'
                    }
                },
                data: {
                    requireLogin: true,
                    roles: ['admin']
                }
            });
        }]);
})(window.angular);