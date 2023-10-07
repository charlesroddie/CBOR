using NUnit.Framework;
using PeterO.Cbor;
using System;

namespace Test
{
  [TestFixture]
  public class CBORExceptionTest
  {
    [Test]
    public void TestConstructor()
    {
      try
      {
        throw new CBORException("Test exception");
      }
      catch (CBORException)
      {
        // NOTE: Intentionally empty
      }
      catch (Exception ex)
      {
        Assert.Fail(ex.ToString());
        throw new InvalidOperationException(string.Empty, ex);
      }
    }
  }
}