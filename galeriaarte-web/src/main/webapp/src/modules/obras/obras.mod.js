
/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

(function (ng) {
    var mod = ng.module("obraModule", ['ui.router']);
    // Configuración de los estados del módulo
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            // En basePath se encuentran los templates y controladores de módulo
            var basePath = 'src/modules/obras/';
            // Mostrar la lista de autores será el estado por defecto del módulo
            $urlRouterProvider.otherwise("/obrasList");
         // Definición del estado 'authorsList' donde se listan los autores
          $stateProvider.state('obras', {
                url: '/obras',
                abstract: true,
                views: {
                    'mainView': {
                        templateUrl: basePath + 'obras.html',
                        controller: 'obraCtrl',
                        controllerAs: 'ctrl'
                    }
                }
                ,
                data: {
                    requireLogin: false,
                    roles: ['administrador', 'cliente','artista']
                }
            }).state('obrasList', {
                url: '/list',
                parent: 'obras',
                views: {
                    'listView': {
                        templateUrl: basePath + 'obras.list.html'
                    }
                }
            }).state('obraDetail', {
                url: '/{obraId:int}/detail',
                parent: 'obras',
                param: {
                    obraId: null
                },
                views: {
                  
                    'detailView': {
                        templateUrl: basePath + 'obras.detail.html',
                        controller: 'obraCtrl',
                        controllerAs: 'ctrl'
                    },
                    'listView': {
                        templateUrl: 'src/modules/comentarios/comentarios.list.html',
                        controller: 'obraCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('obrasCreate', {
                url: '/create',
                parent: 'obras',
                views: {
                    'detailView': {
                        templateUrl: basePath + '/new/obras.new.html',
                        controller: 'obraNewCtrl'
                    }
                }
                ,
                data: {
                    requireLogin: false,
                    roles: ['administrador', 'cliente','artista']
                }
            }).state('obraUpdate', {
                url: '/update/{obraId:int}',
                parent: 'obras',
                param: {
                    obraId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + '/new/obras.new.html',
                        controller: 'obraUpdateCtrl'
                    }
                }
                ,
                data: {
                    requireLogin: false,
                    roles: ['administrador', 'cliente','artista']
                }
            }).state('obraDelete', {
                url: '/delete/{obraId:int}',
                parent: 'obras',
                param: {
                    obraId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + '/delete/obra.delete.html',
                        controller: 'obraDeleteCtrl'
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