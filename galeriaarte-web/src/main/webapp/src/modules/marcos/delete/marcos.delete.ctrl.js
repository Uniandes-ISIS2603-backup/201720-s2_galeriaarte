(function (ng) {
    var mod = ng.module("marcoModule");
    mod.constant("marcosContext", "api/marcos");
    mod.controller('marcoDeleteCtrl', ['$scope', '$http', 'marcosContext', '$state',
        function ($scope, $http, marcosContext, $state) {
            var idMarco = $state.params.marcoId;
            $scope.deleteMarco = function () {
                $http.delete(marcosContext + '/' + idMarco, {}).then(function (response) {
                    $state.go('marcosList', {marcoId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(window.angular);


