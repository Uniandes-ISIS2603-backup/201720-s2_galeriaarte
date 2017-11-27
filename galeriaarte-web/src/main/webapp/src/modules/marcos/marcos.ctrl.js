(function (ng) {
    var mod = ng.module("marcoModule");
    mod.constant("marcosContext", "api/marcos");
    mod.controller('marcoCtrl', ['$scope', '$http', 'marcosContext', '$state',
        function ($scope, $http, marcosContext, $state) {
            $http.get(marcosContext).then(function (response) {
                $scope.marcosRecords = response.data;
            });

            if (($state.params.marcoId !== undefined) && ($state.params.marcoId !== null)) {
                $http.get(marcosContext + '/' + $state.params.marcoId).then(function (response) {
                    $scope.currentMarco = response.data;
                });
            }
        }
    ]);
}
)(window.angular);