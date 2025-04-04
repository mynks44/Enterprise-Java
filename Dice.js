(() => {

    const Dice = class Dice {

        constructor() {
            let args = arguments[0] || {};

            this.size = args.size || 3;
            this.image = args.image || MD.Navigation.url.static + "/sprites/dice/die";
            this.scene = args.scene;
            this.value = args.value || 3;
            this.position = args.position || new THREE.Vector3(0, 0, 0);
            this.rotation = args.rotation || 0;
            this.height = args.height || 2;

            this.animation = {};
            this.animation.on = false;
            this.animation.duration = 500;
            this.animation.target = 0;
            this.animation.end = 0;
            this.animation.speed = args.speed || 200;

            this.initFaces();
            this.initPositions();
            this.face(this.value);
        }

        shuffle(res, duration) {
            return new Promise((ress, rej) => {
                duration = duration || 1500;
                let end = performance.now() + duration;

                this.animation.duration = duration;
                this.animation.end = end;
                this.animation.target = res;
                this.animation.height = new THREE.Vector3(0, this.position.y + this.height, 0);

                this.animation.first = false;
                this.animation.on = true;

                this.animation.promise = ress;
            });
        }

        update(delta) {
            if (!this.animation.on) return;

            let now = performance.now();
            let left = this.animation.end - now;

            if (left <= 0) {
                this.face(this.animation.target);
                this.group.position.setY(this.position.y);
                this.animation.on = false;
                this.animation.promise();
                return;
            }

            let half = this.animation.duration / 2;
            let height = new THREE.Vector3(0, 0, 0).copy(this.position);
            let heightTarget = new THREE.Vector3().copy(this.animation.height);

            if (left >= half) {
                let temp = MD.EasingFunctions.easeOutQuad(1 - ((left / half) - 1));
                height = height.lerp(this.animation.height, temp);
            } else {
                let temp = MD.EasingFunctions.easeInQuad(1 - (left / half));
                height = heightTarget.lerp(height, temp);
            }

            this.group.position.setY(height.y);

            if (left <= 300) {
                if (!this.animation.first) {
                    this.animation.starts = [
                        MD.NodeUtils.rad2deg(this.cube.rotation.x) % 360,
                        MD.NodeUtils.rad2deg(this.cube.rotation.y) % 360,
                        MD.NodeUtils.rad2deg(this.cube.rotation.z) % 360,
                    ];
                    this.animation.targets = this.positions[this.animation.target - 1];

                    this.animation.distance = [
                        this.animation.targets[0] - this.animation.starts[0],
                        this.animation.targets[1] - this.animation.starts[1],
                        this.animation.targets[2] - this.animation.starts[2],
                    ];

                    this.animation.first = true;
                }

                let rate = 1 - (left / 300);

                this.cube.rotation.x = MD.NodeUtils.deg2rad(this.animation.starts[0] + rate * this.animation.distance[0]);
                this.cube.rotation.y = MD.NodeUtils.deg2rad(this.animation.starts[1] + rate * this.animation.distance[1]);
                this.cube.rotation.z = MD.NodeUtils.deg2rad(this.animation.starts[2] + rate * this.animation.distance[2]);
            } else {
                this.cube.rotation.x += MD.NodeUtils.deg2rad(delta * this.animation.speed * MD.NodeUtils.randomFloat(1.5, 6.5, 2));
                this.cube.rotation.y += MD.NodeUtils.deg2rad(delta * this.animation.speed * MD.NodeUtils.randomFloat(1.5, 6.5, 2));
                this.cube.rotation.z += MD.NodeUtils.deg2rad(delta * this.animation.speed * MD.NodeUtils.randomFloat(1.5, 6.5, 2));
            }
        }

        face(val) {
            this.value = val;
            let vals = this.positions[val - 1];

            this.cube.rotation.z = MD.NodeUtils.deg2rad(vals[2]);
            this.cube.rotation.y = MD.NodeUtils.deg2rad(vals[1]);
            this.cube.rotation.x = MD.NodeUtils.deg2rad(vals[0]);
        }

        initPositions() {
            this.positions = [
                [0, 0, 90],
                [0, 0, 360],
                [90, 0, 90],
                [90, 180, 90],
                [0, 270, 180],
                [180, 270, 90]
            ];
        }

        updatePosition(position) {
            this.position = position;
            this.group.position.copy(this.position);
        }

        updateRotation(rot) {
            this.rotation = rot;
            this.group.rotation.y = 0;
            this.group.rotation.y += MD.NodeUtils.deg2rad(this.rotation);
        }

        initFaces() {
            this.group = new THREE.Group();
            this.group.rotation.y += MD.NodeUtils.deg2rad(this.rotation);

            this.geometry = new THREE.BoxGeometry(this.size, this.size, this.size);
            this.textureLoader = new THREE.TextureLoader();

            let texture0 = this.textureLoader.load(this.image + "1.png?v=2");
            let texture1 = this.textureLoader.load(this.image + "6.png?v=2");
            let texture2 = this.textureLoader.load(this.image + "2.png?v=2");
            let texture3 = this.textureLoader.load(this.image + "5.png?v=2");
            let texture4 = this.textureLoader.load(this.image + "4.png?v=2");
            let texture5 = this.textureLoader.load(this.image + "3.png?v=2");

            this.textures = [texture0, texture1, texture2, texture3, texture4, texture5];

            this.materials = [
                new THREE.MeshLambertMaterial({ map: texture0 }),
                new THREE.MeshLambertMaterial({ map: texture1 }),
                new THREE.MeshLambertMaterial({ map: texture2 }),
                new THREE.MeshLambertMaterial({ map: texture3 }),
                new THREE.MeshLambertMaterial({ map: texture4 }),
                new THREE.MeshLambertMaterial({ map: texture5 })
            ];

            this.cube = new THREE.Mesh(this.geometry, this.materials);
            this.cube.castShadow = true;

            this.group.position.copy(this.position);
            this.group.add(this.cube);
            this.scene.add(this.group);
        }

        dispose() {
            for (let e = this.materials.length - 1; e >= 0; e--) {
                this.materials[e].dispose();
            }
            for (let e = this.textures.length - 1; e >= 0; e--) {
                this.textures[e].dispose();
            }

            this.geometry.dispose();
            this.scene.remove(this.group);
        }

        // ✅ NEW METHOD: Set dice face instantly from console
        setFace(val) {
            this.face(val);
            this.group.position.setY(this.position.y); // Reset to original height
            this.animation.on = false;
        }
    };

    MD.Dice = Dice;
    MD.dice = null;

})();
