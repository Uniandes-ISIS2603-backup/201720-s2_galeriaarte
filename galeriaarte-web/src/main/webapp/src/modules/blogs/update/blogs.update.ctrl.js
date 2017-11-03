(
        function (ng) {
            var mod = ng.module("blogModule");
            mod.constant("blogsContext", "api/blogs");
            mod.controller('blogUpdateCtrl', ['$scope', '$http', 'blogsContext', '$state', '$rootScope', '$filter',
                function ($scope, $http, blogsContext, $state, $rootScope, $filter) {
                    $rootScope.edit = true;

                    var idBlog = $state.params.blogId;

                    //Consulto el blog a editar.
                    $http.get(blogsContext + '/' + idBlog).then(function (response) {
                        var blog = response.data;
                        $scope.blogName = blog.name;
                        $scope.blogContenido = blog.contenido;
                        
                    });

                    //funciones para el drag and drop de HTML5 nativo
                    $scope.allowDrop = function (ev) {
                        ev.preventDefault();
                    };

                    $scope.drag = function (ev) {
                        ev.dataTransfer.setData("text", ev.target.id);
                    };

                    $scope.dropAdd = function (ev) {
                        ev.preventDefault();
                        var data = ev.dataTransfer.getData("text");
                        ev.target.appendChild(document.getElementById(data));
                    };

                    $scope.dropDelete = function (ev) {
                        ev.preventDefault();
                        var data = ev.dataTransfer.getData("text");
                        ev.target.appendChild(document.getElementById(data));
                    };

                    $scope.createBlog = function () {
                        $http.put(blogsContext + "/" + idBlog, {
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


