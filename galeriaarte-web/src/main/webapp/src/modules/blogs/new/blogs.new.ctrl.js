(function (ng) {
    var mod = ng.module("blogModule");
    mod.constant("blogsContext", "api/blogs");
    mod.controller('blogNewCtrl', ['$scope', '$http', 'blogsContext', '$state','$rootScope',
        function ($scope, $http, blogsContext, $state, $rootScope) {
            $rootScope.edit = false;
            $scope.createBlog = function () {
                $http.post(blogsContext, {
                    name: $scope.blogName,
                    contenido: $scope.blogContenido
                }).then(function (response) {
                    //Blog created successfully
                    $state.go('blogsList', {blogId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(window.angular);


