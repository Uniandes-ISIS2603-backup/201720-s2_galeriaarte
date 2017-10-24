/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function (ng) {
    var mod = ng.module("galeriaModule", ['ui.router']);
    mod.constant("galeriasContext", "api/galeria");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/galerias/';
            var basePathClientes = 'src/modules/clientes/';
            var basePathCatalogos = 'src/modules/catalgos/';
            var basePathArtistas = 'src/modules/artistas/';
            $urlRouterProvider.otherwise("/galeriasList");
            $stateProvider.state('galerias', {
                url: '/galerias',
                abstract: true,
                views: {
                    'mainView': {
                        templateUrl: basePath + 'galerias.html',
                        controller: 'galeriaCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('galeriasList', {
                url: '/list',
                parent: 'galerias',
                views: {
                    'listView': {
                        templateUrl: basePath + 'galerias.list.html'
                    }
                }
            }).state('galeriaDetail', {
                url: '/{galeriasId:int}/galeria',
                parent: 'galerias',
                param: {
                    galeriasId: null
                },
                views: {
                    'listView': {
                        templateUrl: basePathClientes + 'clientes.list.html',
                        controller: 'galeriaCtrl',
                        controllerAs: 'ctrl'
                    },
                    'detailView': {
                        templateUrl: basePath + 'galerias.detail.html',
                        controller: 'editorialCtrl',
                        controllerAs: 'ctrl'
                    }
                    'listView': 
                    
                }
            });
        }]);
})(window.angular);
