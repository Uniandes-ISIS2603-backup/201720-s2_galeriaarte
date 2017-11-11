(function (ng) {
    var mod = ng.module("comentarioModule", ['ui.router']);
    mod.constant("comentarioContext", "api/comentarios");
    mod.config(['$stateProvider', '$urlRouterProvider', 
        function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/comentarios/';
            var basePathClientes = 'src/modules/clientes/';
            $urlRouterProvider.otherwise("/comentariosList");
            
            $stateProvider.state('comentarios', {
                url: '/comentarios',
                abstract: true,
                views: {
                    'mainView': {
                        templateUrl: basePath + 'comentarios.html',
                        controller: 'comentarioCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('comentariosList', {
                url: '/list',
                parent: 'comentarios',
                views: {
                    'listView': {
                        templateUrl: basePath + 'comentarios.list.html'
                    }
                }
            }).state('comentarioDetail', {
                url: '/{comentarioId:int}/detail',
                parent: 'comentarios',
                param: {
                    comentarioId: null
                },
                views: {
                    'listView': {
//                        templateUrl: basePathClientes + 'clientes.list.html',
//                        controller: 'comentarioCtrl',
//                        controllerAs: 'ctrl'
                        templateUrl: basePath + 'comentarios.list.html'
                    },
                    'detailView': {
                        templateUrl: basePath + 'comentarios.detail.html',
                        controller: 'comentarioCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('comentarioCreate', {
                url: '/create',
                parent: 'comentarios',
                views: {
                    'detailView': {
                        templateUrl: basePath + 'new/comentarios.new.html',
                        controller: 'comentarioNewCtrl'
                    }
                }
            }).state('comentarioUpdate', {
                url: '/update/{comentarioId:int}',
                parent: 'comentarios',
                param: {
                    comentarioId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + 'new/comentarios.new.html',
                        controller: 'comentarioUpdateCtrl'
                    }
                }
            }).state('comentarioDelete', {
                url: '/delete/{comentarioId:int}',
                parent: 'comentarios',
                param: {
                    comentarioId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + 'delete/comentarios.delete.html',
                        controller: 'comentarioDeleteCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
        }]);
})(window.angular);