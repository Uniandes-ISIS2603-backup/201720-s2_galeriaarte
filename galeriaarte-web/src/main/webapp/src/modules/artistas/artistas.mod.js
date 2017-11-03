
/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

(function (ng) {
    var mod = ng.module("artistaModule", ['ui.router']);
    // Configuración de los estados del módulo
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            // En basePath se encuentran los templates y controladores de módulo
            var basePath = 'src/modules/artistas/';
            // Mostrar la lista de autores será el estado por defecto del módulo
            $urlRouterProvider.otherwise("/artistasList");
         // Definición del estado 'authorsList' donde se listan los autores
          $stateProvider.state('artistas', {
                url: '/artistas',
                abstract: true,
                views: {
                    'mainView': {
                        templateUrl: basePath + 'artistas.html',
                        controller: 'artistaCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('artistasList', {
                url: '/list',
                parent: 'artistas',
                views: {
                    'listView': {
                        templateUrl: basePath + 'artistas.list.html'
                    }
                }
            }).state('artistaDetail', {
                url: '/{artistaId:int}/detail',
                parent: 'artistas',
                param: {
                    artistaId: null
                },
                views: {
                  
                    'detailView': {
                        templateUrl: basePath + 'artistas.detail.html',
                        controller: 'artistaCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('artistasCreate', {
                url: '/create',
                parent: 'artistas',
                views: {
                    'detailView': {
                        templateUrl: basePath + '/new/artistas.new.html',
                        controller: 'artistaNewCtrl'
                    }
                }
            }).state('artistaUpdate', {
                url: '/update/{artistaId:int}',
                parent: 'artistas',
                param: {
                    artistaId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + '/new/artistas.new.html',
                        controller: 'artistaUpdateCtrl'
                    }
                }
            }).state('artistaDelete', {
                url: '/delete/{artistaId:int}',
                parent: 'artistas',
                param: {
                    authorId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + '/delete/artista.delete.html',
                        controller: 'artistaDeleteCtrl'
                    }
                }
            });

        }]);
})(window.angular);

 
