
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
          $stateProvider.state('artistasList', {
                // Url que aparecerá en el browser
                url: '/artistas/list',
                views: {
                    'mainView': {
                        templateUrl: basePath + 'artistas.list.html',
                        controller: 'artistasCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
        }
    ]);
}) (window.angular);


