
package arg.jorge.mainquest.domain;

import jakarta.persistence.*;

    @MappedSuperclass
    public abstract class Persistible {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        public Long getId() {
            return id;
        }

        protected void setId(Long id) {
            this.id = id;
        }
    }



