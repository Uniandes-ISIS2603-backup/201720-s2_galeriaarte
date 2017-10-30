(function (ng){
    var mod = ng.module("compraModule", ['obraModule', 'ui.router']);
    mod.constant("comprasContext", "compras");
    mod.constant("obrasContext", "api/obras");
    
    mod.config(['$stateProvider', '$urlRouterProvider',
    function($stateProvider, $urlRouterProvider){
        var basePath = 'src/modules/compras/';
        $urlRouterProvider.otherwise("/comprasList");
        
        $stateProvider.state('compras',{
        url: '/compras',
        abstract: true,
        parent: 'obraDetail',
        views: {
            'childrenView': {
                 templateUrl: basePath + 'compras.html',
            }
        }
        }).state('comprasList', {
            url:'/list',
            parent: 'compras',
            views:{
                'listView':{
                    templateUrl: basePath + 'compras.list.html',
                    controller: 'comprasCtrl',
                    controllerAs: 'ctrl'
                }
            }
        })
    }])
})(window.angular);





