
(function (ng){
    var mod = ng.module("pagoModule", ['ui.router']);
    
    mod.config(['$stateProvider', '$urlRouterProvider',
    function($stateProvider, $urlRouterProvider){
        var basePath = 'src/modules/pagos/';
        
        $urlRouterProvider.otherwise("/pagosList");
        
        $stateProvider.state('pagosList',{
        url: '/pagos/list',
        views: {
            'mainView': {
                 templateUrl: basePath + 'pagos.list.html',
                 controller: 'pagoCtrl',
                 controllerAs: 'ctrl'
            }
        }
        })
    }])
})(window.angular);


