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
                    'listView': {
                        templateUrl: basePath + 'marcos.list.html'
                    },
                    'detailView': {
                        templateUrl: basePath + 'marcos.detail.html',
                        controller: 'marcoCtrl',
                        controllerAs: 'ctrl'
                    }

                }

            });
        }]);
})(window.angular);

