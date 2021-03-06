(function (ng) {
    var mod = ng.module("marcoModule", ['ui.router']);
    mod.constant("marcosContext", "api/marcos");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/marcos/';
            $urlRouterProvider.otherwise("/marcosList");

            $stateProvider.state('marcos', {
                url: '/marcos',
                abstract: true,
                views: {
                    'mainView': {
                        templateUrl: basePath + 'marcos.html',
                        controller: 'marcoCtrl',
                        controllerAs: 'ctrl'
                    }
                }
                ,
                data: {
                    requireLogin: false,
                    roles: ['administrador']
                }
                
            }).state('marcosList', {
                url: '/list',
                parent: 'marcos',
                views: {
                    'listView': {
                        templateUrl: basePath + 'marcos.list.html'
                    }
                }
            }).state('marcoDetail', {
                url: '/{marcoId:int}/detail',
                parent: 'marcos',
                param: {
                    marcoId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + 'marcos.detail.html',
                        controller: 'marcoCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('marcosCreate', {
                url: '/create',
                parent: 'marcos',
                views: {
                    'detailView': {
                        templateUrl: basePath + '/new/marcos.new.html',
                        controller: 'marcoNewCtrl'
                    }
                },
                data: {
                    requireLogin: true,
                    roles: ['administrador']
                }
            }).state('marcoUpdate', {
                url: '/update/{marcoId:int}',
                parent: 'marcos',
                param: {
                    marcoId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + '/new/marcos.new.html',
                        controller: 'marcoUpdateCtrl'
                    }
                },
                data: {
                    requireLogin: true,
                    roles: ['administrador']
                }
            }).state('marcoDelete', {
                url: '/delete/{marcoId:int}',
                parent: 'marcos',
                param: {
                    marcoId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + '/delete/marco.delete.html',
                        controller: 'marcoDeleteCtrl'
                    }
                },
                data: {
                    requireLogin: true,
                    roles: ['administrador']
                }
            });
        }]);
})(window.angular);
