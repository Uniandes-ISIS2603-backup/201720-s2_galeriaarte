(function (ng) {
    var mod = ng.module("blogModule", ['ui.router']);
    mod.constant("blogsContext", "api/blogs");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/blogs/';
            $urlRouterProvider.otherwise("/blogsList");

            $stateProvider.state('blogs', {
                url: '/blogs',
                abstract: true,
                 views: {
                    'mainView': {
                        templateUrl: basePath + 'blogs.html',
                        controller: 'blogCtrl',
                        controllerAs: 'ctrl'
                    },
                data: {
                    requireLogin: false,
                    roles: ['administrador']
                }
                }
            }).state('blogsList', {
                url: '/list',
                parent: 'blogs',
                views: {
                    'listView': {
                        templateUrl: basePath + 'blogs.list.html'
                    }
                }
            }).state('blogDetail', {
                url: '/{blogId:int}/detail',
                parent: 'blogs',
                param: {
                    blogId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + 'blogs.detail.html',
                        controller: 'blogCtrl',
                        controllerAs: 'ctrl'
                    }

                }

            }).state('blogsCreate', {
                url: '/create',
                parent: 'blogs',
                views: {
                    'detailView': {
                        templateUrl: basePath + '/new/blogs.new.html',
                        controller: 'blogNewCtrl'
                    }
                },
                data: {
                    requireLogin: true,
                    roles: ['artista']
                }
            }).state('blogUpdate', {
                url: '/update/{blogId:int}',
                parent: 'blogs',
                param: {
                    blogId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + '/new/blogs.new.html',
                        controller: 'blogUpdateCtrl'
                    },
                data: {
                    requireLogin: true,
                    roles: ['artista']
                }
                }
            }).state('blogDelete', {
                url: '/delete/{blogId:int}',
                parent: 'blogs',
                param: {
                   blogId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + '/delete/blog.delete.html',
                        controller: 'blogDeleteCtrl'
                    },
                data: {
                    requireLogin: true,
                    roles: ['artista']
                }
                }
            });
        }]);
})(window.angular);