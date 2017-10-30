/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function (ng) {
    // Definición del módulo
    var mod = ng.module("catalogoModule", ['ui.router']);

    // Configuración de los estados del módulo
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            // En basePath se encuentran los templates y controladores de módulo
            var basePath = 'src/modules/catalogos/';
            // Mostrar la lista de blogs será el estado por defecto del módulo
            $urlRouterProvider.otherwise("/catalogosList");
            // Definición del estado 'blogsList' donde se listan los blogs
            $stateProvider.state('catalogosList', {
                // Url que aparecerá en el browser
                url: '/catalogos/list',
                views: {
                    'mainView': {
                        templateUrl: basePath + 'catalogos.list.html',
                        controller: 'catalogoCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
        }
    ]);
})(window.angular);
