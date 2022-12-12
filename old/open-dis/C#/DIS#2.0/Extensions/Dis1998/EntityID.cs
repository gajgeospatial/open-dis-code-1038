using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace OpenDis.Dis1998
{
    public partial class EntityID
    {
        public override string ToString()
        {
            return string.Format("{0:00}{1:00}{2:00}", this.Site, this.Application, this.Entity);
        }
    }
}
