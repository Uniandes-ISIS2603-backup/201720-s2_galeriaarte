<<<<<<< HEAD
(function (ng) {
    var mod = ng.module("compraModule", ['ui.router']);
    mod.constant("comprasContext", "api/compras");
    mod.config(['$stateProvider', '$urlRouterProvider', 
        function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/compras/';
            var basePathPagos = 'src/modules/pagos/';         
            $urlRouterProvider.otherwise("/comprasList");
            
            $stateProvider.state('compras', {
                url: '/compras',
                abstract: true,
                views: {
                    'mainView': {
                        templateUrl: basePath + 'compras.html',
                        controller: 'compraCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('comprasList', {
                url: '/list',
                parent: 'compras',
                views: {
                    'listView': {
                        templateUrl: basePath + 'compras.list.html'
                    }
                }
            }).state('compraDetail', {
                url: '/{compraId:int}/detail',
                parent: 'compra',
                param: {
                    compraId: null
                },
                views: {
                    'listView': {
                        templateUrl: basePathPagos + 'pagos.list.html',
                        controller: 'compraCtrl',
                        controllerAs: 'ctrl'
                    },
                    'detailView': {
                        templateUrl : basePath + 'compras.detail.html',
                        controller: 'compraCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('compraCreate', {
                url: '/create',
                parent: 'compras',
                views: {
                    'detailView': {
                        templateUrl: basePath + 'new/compras.new.html',
                        controller: 'compraNewCtrl'
                    }
                }
            }).state('compraUpdate', {
                url: '/update/{compraId:int}',
                parent: 'compras',
                param: {
                    comprad: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + 'new/compras.new.html',
                        controller: 'compraUpdateCtrl'
                    }
                }
            }).state('compraDelete', {
                url: '/delete/{compraId:int}',
                parent: 'compras',
                param: {
                    compraId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + 'delete/compras.delete.html',
                        controller: 'compraDeleteCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
        }]);
})(window.angular);
=======
(function (ng){
    //var mod = ng.module("compraModule", ['obraModule', 'ui.router']);
    var mod = ng.module("compraModule", ['compraModule', 'ui.router']);
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

>>>>>>> origin/master




