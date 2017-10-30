
(function (ng){
    var mod = ng.module("compraModule", ['ui.router']);
    
    mod.config(['$stateProvider', '$urlRouterProvider',
    function($stateProvider, $urlRouterProvider){
        var basePath = 'src/modules/compras/';
        
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



