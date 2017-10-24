/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function (ng) {
    //Definición del modulo
    var mod = ng.module("catalogoModule", ['ui.router']);
    // mod.constant("catalogosContext", "api/catalogos");
    
    //Configuración de los estados del módulo
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) 
        {
            // En basePath se encuentran los templates y controladores de módulo
            var basePath = 'src/modules/catalogos/';
           // var basePathObras = 'src/modules/obrass/';
            // Mostrar la lista de catalogos será el estado por defecto del módulo
            $urlRouterProvider.otherwise("/catalogosList");
            // Definición del estado 'catalogosList' donde se listan los catalogos
            $stateProvider.state('catalogos', {
                // Url que aparecerá en el browser
                url: '/catalogos',
                abstract: true,
                views: {
                    'mainView': {
                        templateUrl: basePath + 'catalogos.list.html',
                        controller: 'catalogosCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            
        }]);
                    //.state('catalogosList', {
             //   url: '/list',
              //  parent: 'catalogos',
               // views: {
                 //   'listView': {
                //        templateUrl: basePath + 'catalogos.list.html'
                //    }
             //   }
           // }).state('catalogoDetail', {
               // url: '/{catalogosId:int}/catalogo',
               // parent: 'catalogos',
                //param: {
                 //   catalogosId: null
               // },
               // views: {
                  //  'listView': {
                   //     templateUrl: basePathObras + 'obras.list.html',
                   //     controller: 'editorialCtrl',
                   //     controllerAs: 'ctrl'
                   // },
                  //  'detailView': {
                  //      templateUrl: basePath + 'catalogos.detail.html',
                  //      controller: 'editorialCtrl',
                  //      controllerAs: 'ctrl'
                 //   }}});
})(window.angular);
