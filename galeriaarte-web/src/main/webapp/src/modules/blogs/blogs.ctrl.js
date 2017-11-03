(function (ng) {
    var mod = ng.module("blogModule");
    mod.constant("blogsContext", "api/blogs");
    mod.controller('blogCtrl', ['$scope', '$http', 'blogsContext', '$state',
        function ($scope, $http, blogsContext, $state) {
            $http.get(blogsContext).then(function (response) {
                $scope.blogsRecords = response.data;
            });

            if (($state.params.blogId !== undefined) && ($state.params.blogId !== null)) {
                $http.get(blogsContext + '/' + $state.params.blogId).then(function (response) {
                    $scope.currentBlog = response.data;
                });
            }
        }
    ]);
}
)(window.angular);


