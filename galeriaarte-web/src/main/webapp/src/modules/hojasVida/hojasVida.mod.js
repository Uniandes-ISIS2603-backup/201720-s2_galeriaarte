
/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

(function (ng) {
    var mod = ng.module("hojaVidaModule", ['ui.router']);
    // Configuración de los estados del módulo
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            // En basePath se encuentran los templates y controladores de módulo
            var basePath = 'src/modules/hojasVida/';
            // Mostrar la lista de autores será el estado por defecto del módulo
            $urlRouterProvider.otherwise("/hojasVidaList");
         // Definición del estado 'authorsList' donde se listan los autores
          $stateProvider.state('hojasVida', {
                url: '/hojasVida',
                abstract: true,
                views: {
                    'mainView': {
                        templateUrl: basePath + 'hojasVida.html',
                        controller: 'hojaVidaCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('hojasVidaList', {
                url: '/list',
                parent: 'hojasVida',
                views: {
                    'listView': {
                        templateUrl: basePath + 'hojasVida.list.html'
                    }
                }
            }).state('hojaVidaDetail', {
                url: '/{hojaVidaId:int}/detail',
                parent: 'hojasVida',
                param: {
                    hojaVidaId: null
                },
                views: {
                  
                    'detailView': {
                        templateUrl: basePath + 'hojasVida.detail.html',
                        controller: 'hojaVidaCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('hojasVidaCreate', {
                url: '/create',
                parent: 'hojasVida',
                views: {
                    'detailView': {
                        templateUrl: basePath + '/new/hojasVida.new.html',
                        controller: 'hojaVidaNewCtrl'
                    }
                }
            }).state('hojaVidaUpdate', {
                url: '/update/{hojaVidaId:int}',
                parent: 'hojasVida',
                param: {
                    hojaVidaId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + '/new/hojasVida.new.html',
                        controller: 'hojaVidaUpdateCtrl'
                    }
                }
            }).state('hojaVidaDelete', {
                url: '/delete/{hojaVidaId:int}',
                parent: 'hojasVida',
                param: {
                    hojaVidaId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + '/delete/hojaVida.delete.html',
                        controller: 'hojaVidaDeleteCtrl'
                    }
                }
            });

        }]);
})(window.angular);

 
