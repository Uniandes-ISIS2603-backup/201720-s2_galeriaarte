(function (ng) {
    var mod = ng.module("blogModule", ['artistaModule', 'ui.router']);
    mod.constant("blogsContext", "blogs");
    mod.constant("artistasContext", "api/artistas");

    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/blogs/';
            $urlRouterProvider.otherwise("/blogsList");

            $stateProvider.state('blogs', {
                url: '/blogs',
                abstract: true,
                parent: 'artistaDetail',
                views: {
                    childrenView: {
                        templateUrl: basePath + 'blogs.html'
                    }
                }
            }).state('blogsList', {
                url: '/list',
                parent: 'blogs',
                views: {
                    'listView': {
                        templateUrl: basePath + 'blogs.list.html',
                        controller: 'blogsCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
        }]);
})(window.angular);