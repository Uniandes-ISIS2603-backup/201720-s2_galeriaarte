(function (ng) {
    var mod = ng.module("blogModule");
    mod.constant("blogsContext", "api/blogs");
    mod.controller('blogDeleteCtrl', ['$scope', '$http', 'blogsContext', '$state',
        function ($scope, $http, blogsContext, $state) {
            var idBlog = $state.params.blogId;
            $scope.deleteBlog = function () {
                $http.delete(blogsContext + '/' + idBlog, {}).then(function (response) {
                    $state.go('blogsList', {blogId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(window.angular);


