/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function (ng){
    var mod = ng.module("comprasModule", ['ui.router']);
    
    mod.config(['$stateProvider', '$urlRouterProvider',
    function($stateProvider, $urlRouterProvider){
        var basePath = 'src/modules/compras';
        
        $urlRouterProvider.otherwise("/comprasList");
        
        $stateProvider.state('comprasList',{
        url: '/compras/list',
        views: {
            'mainView': {
                 templateUrl: basePath + 'compras.list.html',
                 controller: 'compraCtrl',
                 controllerAs: 'ctrl'
            }
        }
        })
    }])
})(window.angular);

