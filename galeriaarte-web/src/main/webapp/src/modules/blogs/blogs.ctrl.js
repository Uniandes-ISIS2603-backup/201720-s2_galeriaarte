(function (ng) {
    var mod = ng.module("blogModule");
    mod.constant("blogsContext", "blogs");
    mod.constant("artistasContext", "api/artistas");
    mod.controller('blogsCtrl', ['$scope', '$http', 'artistasContext', '$state', 'blogsContext',
        function ($scope, $http, artistasContext, $state, blogsContext) {
            $http.get(artistasContext + '/' + $state.params.artistaId + '/' + blogsContext).then(function (response) {
                $scope.blogsRecords = response.data;
            });
        }
    ]);
}
)(window.angular);


